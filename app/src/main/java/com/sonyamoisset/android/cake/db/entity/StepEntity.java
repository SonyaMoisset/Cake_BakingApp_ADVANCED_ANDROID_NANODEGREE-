package com.sonyamoisset.android.cake.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "step")
@SuppressWarnings("unused")
public class StepEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "room_id")
    private int roomId;

    @ColumnInfo(name = "recipe_id")
    private int recipeId;

    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("shortDescription")
    @ColumnInfo(name = "short_description")
    private String shortDescription;

    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("videoURL")
    @Expose
    @ColumnInfo(name = "video_url")
    private String videoURL;

    @SerializedName("thumbnailURL")
    @Expose
    @ColumnInfo(name = "thumbnail_url")
    private String thumbnailURL;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
