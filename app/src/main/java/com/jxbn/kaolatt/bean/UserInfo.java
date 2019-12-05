package com.jxbn.kaolatt.bean;

/**
 * Created by hecuncun on 2019/8/22
 */
public class UserInfo {

    /**
     * user : {"id":1,"loginName":"admin","realName":"管理员","org":1,"orgName":"森林消防局","roles":"1,2","orgEntity":{"id":1,"orgName":"森林消防局","orgCode":"0001","orgOrder":1,"orgDesc":"森林消防局","lng":116.363,"lat":39.9867,"radius":50,"parent":null,"parentName":null,"childCount":0}}
     * socketport : 3307
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJyZWFsTmFtZSI6IueuoeeQhuWRmCIsIm9yZ05hbWUiOiLmo67mnpfmtojpmLLlsYAiLCJvcmciOjEsImxvZ2luTmFtZSI6ImFkbWluIiwicm9sZXMiOlsxLDJdLCJpc3MiOiJuY2kiLCJpZCI6MSwiaWF0IjoxNTY2NDM1ODY5LCJqdGkiOiJRTUlCbUQifQ.FmUa66ULjzyh5Iwx4BQN_fdLXvdRvfzGu6XD68tOYyydkmpD1aTIUrGGiaplmx0AKoro4uBRmPDmmoi2iWB_13FD56Ar-cfwSAlHvV6PXaLFdeXqz0Lbu5BIEG7lCMABaM46X6MVOHML45d3JQo_gVYLZcf-b1WD3PQw9oVEDTQ
     */

    private UserBean user;
    private int socketport;
    private String token;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getSocketport() {
        return socketport;
    }

    public void setSocketport(int socketport) {
        this.socketport = socketport;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserBean {
        /**
         * id : 1
         * loginName : admin
         * realName : 管理员
         * org : 1
         * orgName : 森林消防局
         * roles : 1,2
         * orgEntity : {"id":1,"orgName":"森林消防局","orgCode":"0001","orgOrder":1,"orgDesc":"森林消防局","lng":116.363,"lat":39.9867,"radius":50,"parent":null,"parentName":null,"childCount":0}
         */

        private int id;
        private String loginName;
        private String realName;
        private int org;
        private String orgName;
        private String roles;
        private OrgEntityBean orgEntity;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getOrg() {
            return org;
        }

        public void setOrg(int org) {
            this.org = org;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public OrgEntityBean getOrgEntity() {
            return orgEntity;
        }

        public void setOrgEntity(OrgEntityBean orgEntity) {
            this.orgEntity = orgEntity;
        }

        public static class OrgEntityBean {
            /**
             * id : 1
             * orgName : 森林消防局
             * orgCode : 0001
             * orgOrder : 1
             * orgDesc : 森林消防局
             * lng : 116.363
             * lat : 39.9867
             * radius : 50
             * parent : null
             * parentName : null
             * childCount : 0
             */

            private int id;
            private String orgName;
            private String orgCode;
            private int orgOrder;
            private String orgDesc;
            private double lng;
            private double lat;
            private int radius;
            private Object parent;
            private Object parentName;
            private int childCount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public String getOrgCode() {
                return orgCode;
            }

            public void setOrgCode(String orgCode) {
                this.orgCode = orgCode;
            }

            public int getOrgOrder() {
                return orgOrder;
            }

            public void setOrgOrder(int orgOrder) {
                this.orgOrder = orgOrder;
            }

            public String getOrgDesc() {
                return orgDesc;
            }

            public void setOrgDesc(String orgDesc) {
                this.orgDesc = orgDesc;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public int getRadius() {
                return radius;
            }

            public void setRadius(int radius) {
                this.radius = radius;
            }

            public Object getParent() {
                return parent;
            }

            public void setParent(Object parent) {
                this.parent = parent;
            }

            public Object getParentName() {
                return parentName;
            }

            public void setParentName(Object parentName) {
                this.parentName = parentName;
            }

            public int getChildCount() {
                return childCount;
            }

            public void setChildCount(int childCount) {
                this.childCount = childCount;
            }
        }
    }
}
