package com.mannal.server.entity.survey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey_category")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SurveyEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="survey_category_id", updatable = false, nullable = false)
    private UUID id;

    private String title;
    private String code;
    private String use_yn;
    private Date reg_dt;
    private Date edit_dt;
    private Date del_dt;

    @OneToMany(mappedBy = "surveyEntity")
    private List<SurveySubCategoryEntity> surveySubCategories;
}
