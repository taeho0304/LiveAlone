package com.ssafy.db.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 유저 필터 모델 정의.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Userfilter extends BaseEntity implements Serializable {
    @Id
    @ManyToOne
    private CommercialCategory commercialCategory;

    @OneToOne( fetch = FetchType.LAZY)
<<<<<<< HEAD

//    @JoinColumn(unique = true)

     private User user;
=======
//    @JoinColumn(unique = true)
    private User user;
>>>>>>> c0ebc8e9ae59c1b82f017c027fb2a51f5d38c7a3
}