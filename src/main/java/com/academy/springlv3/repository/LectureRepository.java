package com.academy.springlv3.repository;

import com.academy.springlv3.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findLectureByLectureId(Long lectureId);

}
