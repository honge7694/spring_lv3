package com.academy.springlv3.repository;

import com.academy.springlv3.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findLectureByIdOrderByCreatedAt(Long lectureId);
    List<Lecture> findLecturesByCategoryOrderByCreatedAt(String category);
    List<Lecture> findLecturesByTeacherId(Long teacherId);
}
