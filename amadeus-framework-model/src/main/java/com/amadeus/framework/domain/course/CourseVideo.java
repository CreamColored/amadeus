package com.amadeus.framework.domain.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_video", schema = "amadeus")
@EntityListeners(AuditListener.class)
public class CourseVideo implements Serializable {

    private static final long serialVersionUID = -7302750419297361973L;

    @Id
    @Size(min = 32, max = 32, message = "id为32位")
    private String videoId;

    private Long size;

    private Integer chapter;

    private String name;

    private String url;

    private String format;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private CourseInfo courseInfo;

}
