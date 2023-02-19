package com.dmitrenko.imageanalyzer.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "images")
@ToString(callSuper = true, exclude = "images")
@Table(name = "image_object")
public class ImageObject extends BaseEntity {

    @Column(name = "object_tag", nullable = false)
    private String tag;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "objects")
    private Set<Image> images = new HashSet<>();
}
