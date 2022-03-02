package com.mannal.server.entity.survey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey_item_option")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SurveyItemOptionEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="survey_item_option_id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String title;

    @NotNull
    private Integer score;

    @NotNull
    private Integer orderNum;

    @Column
    private String useYn;

    @Column
    private Date regDt;

    @Column
    private Date editDt;

    @Column
    private Date delDt;

    @NotNull
    @ManyToOne
    @JoinColumn(name="survey_item_id")
    private SurveyItemEntity surveyItemEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SurveyItemOptionEntity that = (SurveyItemOptionEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
