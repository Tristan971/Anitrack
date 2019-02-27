package moe.anitrack.thirdparties.thirdparty.local.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import moe.anitrack.thirdparties.common.model.media.ImmutableMediaInfo;
import moe.anitrack.thirdparties.common.model.media.MediaInfo;
import moe.anitrack.thirdparties.thirdparty.local.repository.MediaInfoConverter;

@Entity
@Table(name = "media_known")
public class LocalSimpleMedia {

    @Id
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Convert(converter = MediaInfoConverter.class)
    private ImmutableMediaInfo relatedInfo;

    public LocalSimpleMedia() {
    }

    public LocalSimpleMedia(String name, MediaInfo relatedInfo) {
        this.name = name;
        this.relatedInfo = ImmutableMediaInfo.copyOf(relatedInfo);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocalSimpleMedia that = (LocalSimpleMedia) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(relatedInfo, that.relatedInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, relatedInfo);
    }

    @Override
    public String toString() {
        return "LocalSimpleMedia{" +
               "name='" + name + '\'' +
               ", relatedInfo=" + relatedInfo +
               '}';
    }

}
