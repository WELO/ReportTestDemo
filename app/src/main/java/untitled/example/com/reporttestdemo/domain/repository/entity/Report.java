package untitled.example.com.reporttestdemo.domain.repository.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Amy on 2019/1/2
 */
@Entity
public class Report {

    @PrimaryKey
    private long id;
    private String title;
    private String content;

    public Report() {
    }

    private Report(Builder builder) {
        id = builder.id;
        title = builder.title;
        content = builder.content;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private long id;
        private String title;
        private String content;

        private Builder() {
        }

        public Builder setId(long val) {
            id = val;
            return this;
        }

        public Builder setTitle(String val) {
            title = val;
            return this;
        }

        public Builder setContent(String val) {
            content = val;
            return this;
        }

        public Report build() {
            return new Report(this);
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Report {id = " + id + ", title = " + title + ", content = " + content + " } ";
    }
}
