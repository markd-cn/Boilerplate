package net.boblog.app.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dave on 16/6/21.
 */
@Entity
@Table
public class Photo {
    public enum PhotoPrivacy {
        PUBLIC(1), PRIVATE(0), FRIENDLY(2);

        private int code;

        PhotoPrivacy(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

        public static PhotoPrivacy getPhotoPrivacy(int code) {
            for (PhotoPrivacy p : PhotoPrivacy.values()) {
                if (p.getCode() == code) return p;
            }
            return null;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column private String title;

    @Column private String tags;

    @Column private Integer categoryId;

    @Column private String categoryName;

    @Column private Double longitude; // 东经度，西经的统一为： -1 * 西经度数

    @Column private Double latitude; // 北纬度，南纬的统一为： -1 * 南纬度数

    @Column private Long userId;

    @Column private String userNick;

    @Column private Integer groupId;

    @Column private String groupName;

    @Column private String memo;

    @Column private Integer commentCount;

    @Column private Integer likes;

    @Column private Integer hits;

    @Column private Integer favors;

    @Column private String thumbnailUrl;

    @Column private String bigUrl;

    @Column private String largeUrl;

    @Column private String orginalUrl;

    @Column private int privacy; // 0：私密，1：公开， 2：好友

    @Lob
    @Column private String exif;

    @Lob
    @Column private String galleryLinks; // 所属相册链接缓存JSON, 格式: [{id: 1, label: "gallery A"}, {id: 2, label: "gallery B"}]

    @Column private Date shootedAt; // 拍摄日期

    @Column private Date editedAt; // 编辑日期

    @Column private Date uploadedAt; // 上传日期

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getFavors() {
        return favors;
    }

    public void setFavors(Integer favors) {
        this.favors = favors;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getBigUrl() {
        return bigUrl;
    }

    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getOrginalUrl() {
        return orginalUrl;
    }

    public void setOrginalUrl(String orginalUrl) {
        this.orginalUrl = orginalUrl;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public String getExif() {
        return exif;
    }

    public void setExif(String exif) {
        this.exif = exif;
    }

    public String getGalleryLinks() {
        return galleryLinks;
    }

    public void setGalleryLinks(String galleryLinks) {
        this.galleryLinks = galleryLinks;
    }

    public Date getShootedAt() {
        return shootedAt;
    }

    public void setShootedAt(Date shootedAt) {
        this.shootedAt = shootedAt;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public PhotoPrivacy getPhotoPrivacy() {
        return PhotoPrivacy.getPhotoPrivacy(this.privacy);
    }

    public void setPhotoPrivacy(PhotoPrivacy privacy) {
        this.privacy = privacy.getCode();
    }
}
