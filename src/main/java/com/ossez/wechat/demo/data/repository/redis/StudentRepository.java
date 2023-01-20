package com.ossez.wechat.demo.data.repository.redis;


import com.ossez.wechat.demo.model.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {}
