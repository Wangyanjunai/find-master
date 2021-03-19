package com.potato369.find.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlacklistRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BlacklistRecordExample() {
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

        public Criteria andOwnerUserIdIsNull() {
            addCriterion("owner_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdIsNotNull() {
            addCriterion("owner_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdEqualTo(Long value) {
            addCriterion("owner_user_id =", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdNotEqualTo(Long value) {
            addCriterion("owner_user_id <>", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdGreaterThan(Long value) {
            addCriterion("owner_user_id >", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("owner_user_id >=", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdLessThan(Long value) {
            addCriterion("owner_user_id <", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdLessThanOrEqualTo(Long value) {
            addCriterion("owner_user_id <=", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdIn(List<Long> values) {
            addCriterion("owner_user_id in", values, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdNotIn(List<Long> values) {
            addCriterion("owner_user_id not in", values, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdBetween(Long value1, Long value2) {
            addCriterion("owner_user_id between", value1, value2, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdNotBetween(Long value1, Long value2) {
            addCriterion("owner_user_id not between", value1, value2, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdIsNull() {
            addCriterion("black_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdIsNotNull() {
            addCriterion("black_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdEqualTo(Long value) {
            addCriterion("black_user_id =", value, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdNotEqualTo(Long value) {
            addCriterion("black_user_id <>", value, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdGreaterThan(Long value) {
            addCriterion("black_user_id >", value, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("black_user_id >=", value, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdLessThan(Long value) {
            addCriterion("black_user_id <", value, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdLessThanOrEqualTo(Long value) {
            addCriterion("black_user_id <=", value, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdIn(List<Long> values) {
            addCriterion("black_user_id in", values, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdNotIn(List<Long> values) {
            addCriterion("black_user_id not in", values, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdBetween(Long value1, Long value2) {
            addCriterion("black_user_id between", value1, value2, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andBlackUserIdNotBetween(Long value1, Long value2) {
            addCriterion("black_user_id not between", value1, value2, "blackUserId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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