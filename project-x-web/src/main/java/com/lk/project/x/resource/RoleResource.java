package com.lk.project.x.resource;

public class RoleResource extends AbstractResource<Long> {

    private String name;

    /**
     * Instantiates a new abstract resource.
     *
     * @param id
     */
    public RoleResource(Long id) {
        super(id);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
