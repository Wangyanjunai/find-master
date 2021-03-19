package com.potato369.find.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReportInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeIsNull() {
            addCriterion("category_type is null");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeIsNotNull() {
            addCriterion("category_type is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeEqualTo(Long value) {
            addCriterion("category_type =", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeNotEqualTo(Long value) {
            addCriterion("category_type <>", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeGreaterThan(Long value) {
            addCriterion("category_type >", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("category_type >=", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeLessThan(Long value) {
            addCriterion("category_type <", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeLessThanOrEqualTo(Long value) {
            addCriterion("category_type <=", value, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeIn(List<Long> values) {
            addCriterion("category_type in", values, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeNotIn(List<Long> values) {
            addCriterion("category_type not in", values, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeBetween(Long value1, Long value2) {
            addCriterion("category_type between", value1, value2, "categoryType");
            return (Criteria) this;
        }

        public Criteria andCategoryTypeNotBetween(Long value1, Long value2) {
            addCriterion("category_type not between", value1, value2, "categoryType");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNull() {
            addCriterion("report_type is null");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNotNull() {
            addCriterion("report_type is not null");
            return (Criteria) this;
        }

        public Criteria andReportTypeEqualTo(String value) {
            addCriterion("report_type =", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotEqualTo(String value) {
            addCriterion("report_type <>", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThan(String value) {
            addCriterion("report_type >", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThanOrEqualTo(String value) {
            addCriterion("report_type >=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThan(String value) {
            addCriterion("report_type <", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThanOrEqualTo(String value) {
            addCriterion("report_type <=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLike(String value) {
            addCriterion("report_type like", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotLike(String value) {
            addCriterion("report_type not like", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeIn(List<String> values) {
            addCriterion("report_type in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotIn(List<String> values) {
            addCriterion("report_type not in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeBetween(String value1, String value2) {
            addCriterion("report_type between", value1, value2, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotBetween(String value1, String value2) {
            addCriterion("report_type not between", value1, value2, "reportType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeIsNull() {
            addCriterion("suggest_type is null");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeIsNotNull() {
            addCriterion("suggest_type is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeEqualTo(String value) {
            addCriterion("suggest_type =", value, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeNotEqualTo(String value) {
            addCriterion("suggest_type <>", value, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeGreaterThan(String value) {
            addCriterion("suggest_type >", value, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeGreaterThanOrEqualTo(String value) {
            addCriterion("suggest_type >=", value, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeLessThan(String value) {
            addCriterion("suggest_type <", value, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeLessThanOrEqualTo(String value) {
            addCriterion("suggest_type <=", value, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeLike(String value) {
            addCriterion("suggest_type like", value, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeNotLike(String value) {
            addCriterion("suggest_type not like", value, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeIn(List<String> values) {
            addCriterion("suggest_type in", values, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeNotIn(List<String> values) {
            addCriterion("suggest_type not in", values, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeBetween(String value1, String value2) {
            addCriterion("suggest_type between", value1, value2, "suggestType");
            return (Criteria) this;
        }

        public Criteria andSuggestTypeNotBetween(String value1, String value2) {
            addCriterion("suggest_type not between", value1, value2, "suggestType");
            return (Criteria) this;
        }

        public Criteria andReportUserIdIsNull() {
            addCriterion("report_user_id is null");
            return (Criteria) this;
        }

        public Criteria andReportUserIdIsNotNull() {
            addCriterion("report_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andReportUserIdEqualTo(Long value) {
            addCriterion("report_user_id =", value, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andReportUserIdNotEqualTo(Long value) {
            addCriterion("report_user_id <>", value, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andReportUserIdGreaterThan(Long value) {
            addCriterion("report_user_id >", value, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andReportUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("report_user_id >=", value, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andReportUserIdLessThan(Long value) {
            addCriterion("report_user_id <", value, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andReportUserIdLessThanOrEqualTo(Long value) {
            addCriterion("report_user_id <=", value, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andReportUserIdIn(List<Long> values) {
            addCriterion("report_user_id in", values, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andReportUserIdNotIn(List<Long> values) {
            addCriterion("report_user_id not in", values, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andReportUserIdBetween(Long value1, Long value2) {
            addCriterion("report_user_id between", value1, value2, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andReportUserIdNotBetween(Long value1, Long value2) {
            addCriterion("report_user_id not between", value1, value2, "reportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdIsNull() {
            addCriterion("being_report_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdIsNotNull() {
            addCriterion("being_report_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdEqualTo(Long value) {
            addCriterion("being_report_user_id =", value, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdNotEqualTo(Long value) {
            addCriterion("being_report_user_id <>", value, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdGreaterThan(Long value) {
            addCriterion("being_report_user_id >", value, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("being_report_user_id >=", value, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdLessThan(Long value) {
            addCriterion("being_report_user_id <", value, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdLessThanOrEqualTo(Long value) {
            addCriterion("being_report_user_id <=", value, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdIn(List<Long> values) {
            addCriterion("being_report_user_id in", values, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdNotIn(List<Long> values) {
            addCriterion("being_report_user_id not in", values, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdBetween(Long value1, Long value2) {
            addCriterion("being_report_user_id between", value1, value2, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andBeingReportUserIdNotBetween(Long value1, Long value2) {
            addCriterion("being_report_user_id not between", value1, value2, "beingReportUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01IsNull() {
            addCriterion("reserve_column01 is null");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01IsNotNull() {
            addCriterion("reserve_column01 is not null");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01EqualTo(String value) {
            addCriterion("reserve_column01 =", value, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01NotEqualTo(String value) {
            addCriterion("reserve_column01 <>", value, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01GreaterThan(String value) {
            addCriterion("reserve_column01 >", value, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01GreaterThanOrEqualTo(String value) {
            addCriterion("reserve_column01 >=", value, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01LessThan(String value) {
            addCriterion("reserve_column01 <", value, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01LessThanOrEqualTo(String value) {
            addCriterion("reserve_column01 <=", value, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01Like(String value) {
            addCriterion("reserve_column01 like", value, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01NotLike(String value) {
            addCriterion("reserve_column01 not like", value, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01In(List<String> values) {
            addCriterion("reserve_column01 in", values, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01NotIn(List<String> values) {
            addCriterion("reserve_column01 not in", values, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01Between(String value1, String value2) {
            addCriterion("reserve_column01 between", value1, value2, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn01NotBetween(String value1, String value2) {
            addCriterion("reserve_column01 not between", value1, value2, "reserveColumn01");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02IsNull() {
            addCriterion("reserve_column02 is null");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02IsNotNull() {
            addCriterion("reserve_column02 is not null");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02EqualTo(String value) {
            addCriterion("reserve_column02 =", value, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02NotEqualTo(String value) {
            addCriterion("reserve_column02 <>", value, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02GreaterThan(String value) {
            addCriterion("reserve_column02 >", value, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02GreaterThanOrEqualTo(String value) {
            addCriterion("reserve_column02 >=", value, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02LessThan(String value) {
            addCriterion("reserve_column02 <", value, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02LessThanOrEqualTo(String value) {
            addCriterion("reserve_column02 <=", value, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02Like(String value) {
            addCriterion("reserve_column02 like", value, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02NotLike(String value) {
            addCriterion("reserve_column02 not like", value, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02In(List<String> values) {
            addCriterion("reserve_column02 in", values, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02NotIn(List<String> values) {
            addCriterion("reserve_column02 not in", values, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02Between(String value1, String value2) {
            addCriterion("reserve_column02 between", value1, value2, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn02NotBetween(String value1, String value2) {
            addCriterion("reserve_column02 not between", value1, value2, "reserveColumn02");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03IsNull() {
            addCriterion("reserve_column03 is null");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03IsNotNull() {
            addCriterion("reserve_column03 is not null");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03EqualTo(String value) {
            addCriterion("reserve_column03 =", value, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03NotEqualTo(String value) {
            addCriterion("reserve_column03 <>", value, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03GreaterThan(String value) {
            addCriterion("reserve_column03 >", value, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03GreaterThanOrEqualTo(String value) {
            addCriterion("reserve_column03 >=", value, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03LessThan(String value) {
            addCriterion("reserve_column03 <", value, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03LessThanOrEqualTo(String value) {
            addCriterion("reserve_column03 <=", value, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03Like(String value) {
            addCriterion("reserve_column03 like", value, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03NotLike(String value) {
            addCriterion("reserve_column03 not like", value, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03In(List<String> values) {
            addCriterion("reserve_column03 in", values, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03NotIn(List<String> values) {
            addCriterion("reserve_column03 not in", values, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03Between(String value1, String value2) {
            addCriterion("reserve_column03 between", value1, value2, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn03NotBetween(String value1, String value2) {
            addCriterion("reserve_column03 not between", value1, value2, "reserveColumn03");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04IsNull() {
            addCriterion("reserve_column04 is null");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04IsNotNull() {
            addCriterion("reserve_column04 is not null");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04EqualTo(String value) {
            addCriterion("reserve_column04 =", value, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04NotEqualTo(String value) {
            addCriterion("reserve_column04 <>", value, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04GreaterThan(String value) {
            addCriterion("reserve_column04 >", value, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04GreaterThanOrEqualTo(String value) {
            addCriterion("reserve_column04 >=", value, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04LessThan(String value) {
            addCriterion("reserve_column04 <", value, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04LessThanOrEqualTo(String value) {
            addCriterion("reserve_column04 <=", value, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04Like(String value) {
            addCriterion("reserve_column04 like", value, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04NotLike(String value) {
            addCriterion("reserve_column04 not like", value, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04In(List<String> values) {
            addCriterion("reserve_column04 in", values, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04NotIn(List<String> values) {
            addCriterion("reserve_column04 not in", values, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04Between(String value1, String value2) {
            addCriterion("reserve_column04 between", value1, value2, "reserveColumn04");
            return (Criteria) this;
        }

        public Criteria andReserveColumn04NotBetween(String value1, String value2) {
            addCriterion("reserve_column04 not between", value1, value2, "reserveColumn04");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}