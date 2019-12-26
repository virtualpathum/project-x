package com.lk.project.x.resource;

public class BlogPostResource extends AbstractResource<Long> {


    private String title;

    private String content;

    /**
     * Instantiates a new abstract resource.
     *
     * @param id
     */

    public BlogPostResource(Long id) {
        super(id);
    }

    public BlogPostResource(){
        super(null);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
