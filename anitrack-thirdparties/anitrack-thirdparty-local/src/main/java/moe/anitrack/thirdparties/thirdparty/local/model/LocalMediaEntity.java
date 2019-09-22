package moe.anitrack.thirdparties.thirdparty.local.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import moe.anitrack.thirdparties.common.model.media.ImmutableMediaInfo;
import moe.anitrack.thirdparties.common.model.media.MediaInfo;

@Entity
@Table(name = "media_known")
public class LocalMediaEntity {

    @Id
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    private ImmutableMediaInfo relatedInfo;

    private int playCount;

    public LocalMediaEntity() {
    }

    public LocalMediaEntity(String name, MediaInfo relatedInfo, int playCount) {
        this.name = name;
        this.relatedInfo = ImmutableMediaInfo.copyOf(relatedInfo);
        this.playCount = playCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaInfo getRelatedInfo() {
        return relatedInfo;
    }

    public void setRelatedInfo(MediaInfo relatedInfo) {
        this.relatedInfo = ImmutableMediaInfo.copyOf(relatedInfo);
    }

    public void setRelatedInfo(ImmutableMediaInfo relatedInfo) {
        this.relatedInfo = relatedInfo;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalMediaEntity)) {
            return false;
        }
        LocalMediaEntity that = (LocalMediaEntity) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
