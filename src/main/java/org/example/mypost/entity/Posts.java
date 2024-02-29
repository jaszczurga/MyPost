package org.example.mypost.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "posts")
public class Posts {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //relation to Likes
    @JsonBackReference(value = "likes")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Likes> likes;

    @JsonProperty("likesCount")
    public int getLikesCount(){
        return likes.size();
    }

    //relation to Comments
    @JsonBackReference(value = "comments")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comments> comments;

}
