package com.potato369.find.mbg.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDetailExample() {
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
        
        public Criteria andDetailIdIsNull() {
            addCriterion("detail_id is null");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNotNull() {
            addCriterion("detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andDetailIdEqualTo(String value) {
            addCriterion("detail_id =", value, "detail_id");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotEqualTo(String value) {
            addCriterion("detail_id <>", value, "detail_id");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThan(String value) {
            addCriterion("detail_id >", value, "detail_id");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThanOrEqualTo(String value) {
            addCriterion("detail_id >=", value, "detail_id");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThan(String value) {
            addCriterion("detail_id <", value, "detail_id");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThanOrEqualTo(String value) {
            addCriterion("detail_id <=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLike(String value) {
            addCriterion("detail_id like", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotLike(String value) {
            addCriterion("detail_id not like", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIn(List<String> values) {
            addCriterion("detail_id in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotIn(List<String> values) {
            addCriterion("detail_id not in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdBetween(String value1, String value2) {
            addCriterion("detail_id between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotBetween(String value1, String value2) {
            addCriterion("detail_id not between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdIsNull() {
            addCriterion("buyer_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdIsNotNull() {
            addCriterion("buyer_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdEqualTo(Long value) {
            addCriterion("buyer_user_id =", value, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdNotEqualTo(Long value) {
            addCriterion("buyer_user_id <>", value, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdGreaterThan(Long value) {
            addCriterion("buyer_user_id >", value, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("buyer_user_id >=", value, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdLessThan(Long value) {
            addCriterion("buyer_user_id <", value, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdLessThanOrEqualTo(Long value) {
            addCriterion("buyer_user_id <=", value, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdIn(List<Long> values) {
            addCriterion("buyer_user_id in", values, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdNotIn(List<Long> values) {
            addCriterion("buyer_user_id not in", values, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdBetween(Long value1, Long value2) {
            addCriterion("buyer_user_id between", value1, value2, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andBuyerUserIdNotBetween(Long value1, Long value2) {
            addCriterion("buyer_user_id not between", value1, value2, "buyerUserId");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNull() {
            addCriterion("product_price is null");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNotNull() {
            addCriterion("product_price is not null");
            return (Criteria) this;
        }

        public Criteria andProductPriceEqualTo(BigDecimal value) {
            addCriterion("product_price =", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotEqualTo(BigDecimal value) {
            addCriterion("product_price <>", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThan(BigDecimal value) {
            addCriterion("product_price >", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_price >=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThan(BigDecimal value) {
            addCriterion("product_price <", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_price <=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceIn(List<BigDecimal> values) {
            addCriterion("product_price in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotIn(List<BigDecimal> values) {
            addCriterion("product_price not in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_price between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_price not between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductQuantityIsNull() {
            addCriterion("product_quantity is null");
            return (Criteria) this;
        }

        public Criteria andProductQuantityIsNotNull() {
            addCriterion("product_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andProductQuantityEqualTo(Integer value) {
            addCriterion("product_quantity =", value, "productQuantity");
            return (Criteria) this;
        }

        public Criteria andProductQuantityNotEqualTo(Integer value) {
            addCriterion("product_quantity <>", value, "productQuantity");
            return (Criteria) this;
        }

        public Criteria andProductQuantityGreaterThan(Integer value) {
            addCriterion("product_quantity >", value, "productQuantity");
            return (Criteria) this;
        }

        public Criteria andProductQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_quantity >=", value, "productQuantity");
            return (Criteria) this;
        }

        public Criteria andProductQuantityLessThan(Integer value) {
            addCriterion("product_quantity <", value, "productQuantity");
            return (Criteria) this;
        }

        public Criteria andProductQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("product_quantity <=", value, "productQuantity");
            return (Criteria) this;
        }

        public Criteria andProductQuantityIn(List<Integer> values) {
            addCriterion("product_quantity in", values, "productQuantity");
            return (Criteria) this;
        }

        public Criteria andProductQuantityNotIn(List<Integer> values) {
            addCriterion("product_quantity not in", values, "productQuantity");
            return (Criteria) this;
        }

        public Criteria andProductQuantityBetween(Integer value1, Integer value2) {
            addCriterion("product_quantity between", value1, value2, "productQuantity");
            return (Criteria) this;
        }

        public Criteria andProductQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("product_quantity not between", value1, value2, "productQuantity");
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