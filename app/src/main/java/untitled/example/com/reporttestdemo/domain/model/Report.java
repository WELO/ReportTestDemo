package untitled.example.com.reporttestdemo.domain.model;

/**
 * Created by Amy on 2019/1/2
 */

public class Report {

    private long id;
    private String title;
    private String content;

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

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
