package com.dmitrenko.imageanalyzer.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@EqualsAndHashCode(callSuper = true, exclude = "objects")
@ToString(callSuper = true, exclude = "objects")
@Table(name = "image")
public class Image extends BaseEntity {

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "image_label")
    private String label;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "image_image_object",
        joinColumns = { @JoinColumn(name = "image_id") },
        inverseJoinColumns = { @JoinColumn(name = "image_object_id") })
    private Set<ImageObject> objects = new HashSet<>();
}
