package com.jlc.bean;

import java.io.Serializable;

public class tree implements Serializable {
    private Integer treeId;

    private String treeName;

    private String treeState;

    private String treeUrl;

    private String permission;

    private static final long serialVersionUID = 1L;

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName == null ? null : treeName.trim();
    }

    public String getTreeState() {
        return treeState;
    }

    public void setTreeState(String treeState) {
        this.treeState = treeState == null ? null : treeState.trim();
    }

    public String getTreeUrl() {
        return treeUrl;
    }

    public void setTreeUrl(String treeUrl) {
        this.treeUrl = treeUrl == null ? null : treeUrl.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", treeId=").append(treeId);
        sb.append(", treeName=").append(treeName);
        sb.append(", treeState=").append(treeState);
        sb.append(", treeUrl=").append(treeUrl);
        sb.append(", permission=").append(permission);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}