package com.mannal.server.entity.survey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
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
@Table(name = "survey_item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SurveyItemEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="survey_item_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private Integer orderNum;
    private String use_yn;
    private Date reg_dt;
    private Date edit_dt;
    private Date del_dt;

    @NotNull
    @ManyToOne
    @JoinColumn(name="survey_sub_category_id")
    private SurveySubCategoryEntity surveySubCategoryEntity;

    @NotNull
    @OneToMany(mappedBy = "surveyItemEntity")
    private List<SurveyItemOptionEntity> surveyItemOptionEntities;

}
