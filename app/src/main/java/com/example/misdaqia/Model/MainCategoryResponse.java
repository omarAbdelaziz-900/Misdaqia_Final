package com.example.misdaqia.Model;

public class MainCategoryResponse {
    /**
     * id : 1
     * name : مجوهرات
     * parent_id : 0
     * image : jew22.jpg
     * created_at : 2019-09-03 05:11:00
     * updated_at : 2019-09-17 06:17:09
     */

    private int id;
    private String name;
    private int parent_id;
    private String image;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }


//    @SerializedName("count of rows")
//    @Expose
//    private String list_count;
//
//    @SerializedName("data")
//    @Expose
//    private List<MainCategory> MainCategory;
//
//    public String getList_count() {
//        return list_count;
//    }
//
//    public void setList_count(String list_count) {
//        this.list_count = list_count;
//    }
//
//    public List<MainCategory> getMainCategory() {
//        return MainCategory;
//    }
//
//    public void setMainCategory(List<MainCategory> mainCategory) {
//        MainCategory = mainCategory;
//    }
}
