package com.fusi24.springcoursemate.repository;

import com.fusi24.springcoursemate.entity.PeerReview;
import com.fusi24.springcoursemate.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "select * from question where reviewid in " +
            "(select reviewid from peer_review where courseid=?1);", nativeQuery = true)
    List<Question> findByCoursecode(long courseid);

    List<Question> findByPeerReview(PeerReview peerReview);

}
