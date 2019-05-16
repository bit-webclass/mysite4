package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;
import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(GalleryVo galleryVo) {
		return sqlSession.insert("gallery.insert", galleryVo);
	}

	public List<GalleryVo> selectList() {
		return sqlSession.selectList("gallery.selectList");
	}
	
	public GalleryVo selectGallery(int no) {
		return sqlSession.selectOne("gallery.selectGallery", no);
	}
	
	public int deleteGallery(int no) {
		return sqlSession.delete("gallery.deleteGallery", no);
	}
}
