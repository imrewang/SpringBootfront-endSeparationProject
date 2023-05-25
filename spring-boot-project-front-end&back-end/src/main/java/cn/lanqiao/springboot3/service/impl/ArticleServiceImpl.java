package cn.lanqiao.springboot3.service.impl;

import cn.lanqiao.springboot3.dao.ArticleDao;
import cn.lanqiao.springboot3.service.ArticleService;
import cn.lanqiao.springboot3.utils.PageResult;
import cn.lanqiao.springboot3.utils.PageUtil;
import cn.lanqiao.springboot3.entity.Article;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Override
    public PageResult getArticlePage(PageUtil pageUtil) {
        List<Article> articleList = articleDao.findArticles(pageUtil);
        int total = articleDao.getTotalArticles(pageUtil);
        PageResult pageResult = new PageResult(articleList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Article queryObject(Integer id) {
        Article article = articleDao.getArticleById(id);
        if (article != null) {
            return article;
        }
        return null;
    }

    @Override
    public List<Article> queryList(Map<String, Object> map) {
        List<Article> articles = articleDao.findArticles(map);
        return articles;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return articleDao.getTotalArticles(map);
    }

    @Override
    public int save(Article article) {
        return articleDao.insertArticle(article);
    }

    @Override
    public int update(Article article) {
        article.setUpdateTime(new Date());
        //分配一个 Date 对象并对其进行初始化，以便它表示分配它的时间，精确到毫秒。
        return articleDao.updArticle(article);
    }

    @Override
    public int delete(Integer id) {
        return articleDao.delArticle(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return articleDao.deleteBatch(ids);
    }
}
