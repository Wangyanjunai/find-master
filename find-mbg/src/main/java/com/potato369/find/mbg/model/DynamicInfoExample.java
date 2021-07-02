package com.potato369.find.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DynamicInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DynamicInfoExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }
        public Criteria andDynamicIdIsNull() {
            addCriterion("dynamic_id is null");
            return (Criteria) this;
        }

        public Criteria andDynamicIdIsNotNull() {
            addCriterion("dynamic_id is not null");
            return (Criteria) this;
        }

        public Criteria andDynamicIdEqualTo(String value) {
            addCriterion("dynamic_id =", value, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdNotEqualTo(String value) {
            addCriterion("dynamic_id <>", value, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdGreaterThan(String value) {
            addCriterion("dynamic_id >", value, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdGreaterThanOrEqualTo(String value) {
            addCriterion("dynamic_id >=", value, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdLessThan(String value) {
            addCriterion("dynamic_id <", value, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdLessThanOrEqualTo(String value) {
            addCriterion("dynamic_id <=", value, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdLike(String value) {
            addCriterion("dynamic_id like", value, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdNotLike(String value) {
            addCriterion("dynamic_id not like", value, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdIn(List<String> values) {
            addCriterion("dynamic_id in", values, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdNotIn(List<String> values) {
            addCriterion("dynamic_id not in", values, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdBetween(String value1, String value2) {
            addCriterion("dynamic_id between", value1, value2, "dynamicId");
            return (Criteria) this;
        }

        public Criteria andDynamicIdNotBetween(String value1, String value2) {
            addCriterion("dynamic_id not between", value1, value2, "dynamicId");
            return (Criteria) this;
        }        

        public Criteria andDynamicStatusIsNull() {
            addCriterion("dynamic_status is null");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusIsNotNull() {
            addCriterion("dynamic_status is not null");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusEqualTo(String value) {
            addCriterion("dynamic_status =", value, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusNotEqualTo(String value) {
            addCriterion("dynamic_status <>", value, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusGreaterThan(String value) {
            addCriterion("dynamic_status >", value, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusGreaterThanOrEqualTo(String value) {
            addCriterion("dynamic_status >=", value, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusLessThan(String value) {
            addCriterion("dynamic_status <", value, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusLessThanOrEqualTo(String value) {
            addCriterion("dynamic_status <=", value, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusLike(String value) {
            addCriterion("dynamic_status like", value, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusNotLike(String value) {
            addCriterion("dynamic_status not like", value, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusIn(List<String> values) {
            addCriterion("dynamic_status in", values, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusNotIn(List<String> values) {
            addCriterion("dynamic_status not in", values, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusBetween(String value1, String value2) {
            addCriterion("dynamic_status between", value1, value2, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andDynamicStatusNotBetween(String value1, String value2) {
            addCriterion("dynamic_status not between", value1, value2, "dynamicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusIsNull() {
            addCriterion("public_status is null");
            return (Criteria) this;
        }

        public Criteria andPublicStatusIsNotNull() {
            addCriterion("public_status is not null");
            return (Criteria) this;
        }

        public Criteria andPublicStatusEqualTo(String value) {
            addCriterion("public_status =", value, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusNotEqualTo(String value) {
            addCriterion("public_status <>", value, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusGreaterThan(String value) {
            addCriterion("public_status >", value, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusGreaterThanOrEqualTo(String value) {
            addCriterion("public_status >=", value, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusLessThan(String value) {
            addCriterion("public_status <", value, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusLessThanOrEqualTo(String value) {
            addCriterion("public_status <=", value, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusLike(String value) {
            addCriterion("public_status like", value, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusNotLike(String value) {
            addCriterion("public_status not like", value, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusIn(List<String> values) {
            addCriterion("public_status in", values, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusNotIn(List<String> values) {
            addCriterion("public_status not in", values, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusBetween(String value1, String value2) {
            addCriterion("public_status between", value1, value2, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andPublicStatusNotBetween(String value1, String value2) {
            addCriterion("public_status not between", value1, value2, "publicStatus");
            return (Criteria) this;
        }

        public Criteria andIsTopicIsNull() {
            addCriterion("is_topic is null");
            return (Criteria) this;
        }

        public Criteria andIsTopicIsNotNull() {
            addCriterion("is_topic is not null");
            return (Criteria) this;
        }

        public Criteria andIsTopicEqualTo(String value) {
            addCriterion("is_topic =", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicNotEqualTo(String value) {
            addCriterion("is_topic <>", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicGreaterThan(String value) {
            addCriterion("is_topic >", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicGreaterThanOrEqualTo(String value) {
            addCriterion("is_topic >=", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicLessThan(String value) {
            addCriterion("is_topic <", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicLessThanOrEqualTo(String value) {
            addCriterion("is_topic <=", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicLike(String value) {
            addCriterion("is_topic like", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicNotLike(String value) {
            addCriterion("is_topic not like", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicIn(List<String> values) {
            addCriterion("is_topic in", values, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicNotIn(List<String> values) {
            addCriterion("is_topic not in", values, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicBetween(String value1, String value2) {
            addCriterion("is_topic between", value1, value2, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicNotBetween(String value1, String value2) {
            addCriterion("is_topic not between", value1, value2, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousIsNull() {
            addCriterion("is_anonymous is null");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousIsNotNull() {
            addCriterion("is_anonymous is not null");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousEqualTo(String value) {
            addCriterion("is_anonymous =", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousNotEqualTo(String value) {
            addCriterion("is_anonymous <>", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousGreaterThan(String value) {
            addCriterion("is_anonymous >", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousGreaterThanOrEqualTo(String value) {
            addCriterion("is_anonymous >=", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousLessThan(String value) {
            addCriterion("is_anonymous <", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousLessThanOrEqualTo(String value) {
            addCriterion("is_anonymous <=", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousLike(String value) {
            addCriterion("is_anonymous like", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousNotLike(String value) {
            addCriterion("is_anonymous not like", value, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousIn(List<String> values) {
            addCriterion("is_anonymous in", values, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousNotIn(List<String> values) {
            addCriterion("is_anonymous not in", values, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousBetween(String value1, String value2) {
            addCriterion("is_anonymous between", value1, value2, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andIsAnonymousNotBetween(String value1, String value2) {
            addCriterion("is_anonymous not between", value1, value2, "isAnonymous");
            return (Criteria) this;
        }

        public Criteria andTopicTitleIsNull() {
            addCriterion("topic_title is null");
            return (Criteria) this;
        }

        public Criteria andTopicTitleIsNotNull() {
            addCriterion("topic_title is not null");
            return (Criteria) this;
        }

        public Criteria andTopicTitleEqualTo(String value) {
            addCriterion("topic_title =", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotEqualTo(String value) {
            addCriterion("topic_title <>", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleGreaterThan(String value) {
            addCriterion("topic_title >", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleGreaterThanOrEqualTo(String value) {
            addCriterion("topic_title >=", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleLessThan(String value) {
            addCriterion("topic_title <", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleLessThanOrEqualTo(String value) {
            addCriterion("topic_title <=", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleLike(String value) {
            addCriterion("topic_title like", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotLike(String value) {
            addCriterion("topic_title not like", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleIn(List<String> values) {
            addCriterion("topic_title in", values, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotIn(List<String> values) {
            addCriterion("topic_title not in", values, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleBetween(String value1, String value2) {
            addCriterion("topic_title between", value1, value2, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotBetween(String value1, String value2) {
            addCriterion("topic_title not between", value1, value2, "topicTitle");
            return (Criteria) this;
        }
        
        public Criteria andLikesIsNull() {
            addCriterion("likes is null");
            return (Criteria) this;
        }

        public Criteria andLikesIsNotNull() {
            addCriterion("likes is not null");
            return (Criteria) this;
        }

        public Criteria andLikesEqualTo(Integer value) {
            addCriterion("likes =", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesNotEqualTo(Integer value) {
            addCriterion("likes <>", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesGreaterThan(Integer value) {
            addCriterion("likes >", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesGreaterThanOrEqualTo(Integer value) {
            addCriterion("likes >=", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesLessThan(Integer value) {
            addCriterion("likes <", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesLessThanOrEqualTo(Integer value) {
            addCriterion("likes <=", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesIn(List<Integer> values) {
            addCriterion("likes in", values, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesNotIn(List<Integer> values) {
            addCriterion("likes not in", values, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesBetween(Integer value1, Integer value2) {
            addCriterion("likes between", value1, value2, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesNotBetween(Integer value1, Integer value2) {
            addCriterion("likes not between", value1, value2, "likes");
            return (Criteria) this;
        }
        
        public Criteria andCommentsIsNull() {
            addCriterion("comments is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNotNull() {
            addCriterion("comments is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsEqualTo(Integer value) {
            addCriterion("comments =", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotEqualTo(Integer value) {
            addCriterion("comments <>", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThan(Integer value) {
            addCriterion("comments >", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThanOrEqualTo(Integer value) {
            addCriterion("comments >=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThan(Integer value) {
            addCriterion("comments <", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThanOrEqualTo(Integer value) {
            addCriterion("comments <=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsIn(List<Integer> values) {
            addCriterion("comments in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotIn(List<Integer> values) {
            addCriterion("comments not in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsBetween(Integer value1, Integer value2) {
            addCriterion("comments between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotBetween(Integer value1, Integer value2) {
            addCriterion("comments not between", value1, value2, "comments");
            return (Criteria) this;
        }        

        public Criteria andApplicationsIsNull() {
            addCriterion("applications is null");
            return (Criteria) this;
        }

        public Criteria andApplicationsIsNotNull() {
            addCriterion("applications is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationsEqualTo(Integer value) {
            addCriterion("applications =", value, "applications");
            return (Criteria) this;
        }

        public Criteria andApplicationsNotEqualTo(Integer value) {
            addCriterion("applications <>", value, "applications");
            return (Criteria) this;
        }

        public Criteria andApplicationsGreaterThan(Integer value) {
            addCriterion("applications >", value, "applications");
            return (Criteria) this;
        }

        public Criteria andApplicationsGreaterThanOrEqualTo(Integer value) {
            addCriterion("applications >=", value, "applications");
            return (Criteria) this;
        }

        public Criteria andApplicationsLessThan(Integer value) {
            addCriterion("applications <", value, "applications");
            return (Criteria) this;
        }

        public Criteria andApplicationsLessThanOrEqualTo(Integer value) {
            addCriterion("applications <=", value, "applications");
            return (Criteria) this;
        }

        public Criteria andApplicationsIn(List<Integer> values) {
            addCriterion("applications in", values, "applications");
            return (Criteria) this;
        }

        public Criteria andApplicationsNotIn(List<Integer> values) {
            addCriterion("applications not in", values, "applications");
            return (Criteria) this;
        }

        public Criteria andApplicationsBetween(Integer value1, Integer value2) {
            addCriterion("applications between", value1, value2, "applications");
            return (Criteria) this;
        }

        public Criteria andApplicationsNotBetween(Integer value1, Integer value2) {
            addCriterion("applications not between", value1, value2, "applications");
            return (Criteria) this;
        }

        public Criteria andSharesIsNull() {
            addCriterion("shares is null");
            return (Criteria) this;
        }

        public Criteria andSharesIsNotNull() {
            addCriterion("shares is not null");
            return (Criteria) this;
        }

        public Criteria andSharesEqualTo(Integer value) {
            addCriterion("shares =", value, "shares");
            return (Criteria) this;
        }

        public Criteria andSharesNotEqualTo(Integer value) {
            addCriterion("shares <>", value, "shares");
            return (Criteria) this;
        }

        public Criteria andSharesGreaterThan(Integer value) {
            addCriterion("shares >", value, "shares");
            return (Criteria) this;
        }

        public Criteria andSharesGreaterThanOrEqualTo(Integer value) {
            addCriterion("shares >=", value, "shares");
            return (Criteria) this;
        }

        public Criteria andSharesLessThan(Integer value) {
            addCriterion("shares <", value, "shares");
            return (Criteria) this;
        }

        public Criteria andSharesLessThanOrEqualTo(Integer value) {
            addCriterion("shares <=", value, "shares");
            return (Criteria) this;
        }

        public Criteria andSharesIn(List<Integer> values) {
            addCriterion("shares in", values, "shares");
            return (Criteria) this;
        }

        public Criteria andSharesNotIn(List<Integer> values) {
            addCriterion("shares not in", values, "shares");
            return (Criteria) this;
        }

        public Criteria andSharesBetween(Integer value1, Integer value2) {
            addCriterion("shares between", value1, value2, "shares");
            return (Criteria) this;
        }

        public Criteria andSharesNotBetween(Integer value1, Integer value2) {
            addCriterion("shares not between", value1, value2, "shares");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeIsNull() {
            addCriterion("attache_type is null");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeIsNotNull() {
            addCriterion("attache_type is not null");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeEqualTo(String value) {
            addCriterion("attache_type =", value, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeNotEqualTo(String value) {
            addCriterion("attache_type <>", value, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeGreaterThan(String value) {
            addCriterion("attache_type >", value, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeGreaterThanOrEqualTo(String value) {
            addCriterion("attache_type >=", value, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeLessThan(String value) {
            addCriterion("attache_type <", value, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeLessThanOrEqualTo(String value) {
            addCriterion("attache_type <=", value, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeLike(String value) {
            addCriterion("attache_type like", value, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeNotLike(String value) {
            addCriterion("attache_type not like", value, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeIn(List<String> values) {
            addCriterion("attache_type in", values, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeNotIn(List<String> values) {
            addCriterion("attache_type not in", values, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeBetween(String value1, String value2) {
            addCriterion("attache_type between", value1, value2, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheTypeNotBetween(String value1, String value2) {
            addCriterion("attache_type not between", value1, value2, "attacheType");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberIsNull() {
            addCriterion("attache_number is null");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberIsNotNull() {
            addCriterion("attache_number is not null");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberEqualTo(Integer value) {
            addCriterion("attache_number =", value, "attacheNumber");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberNotEqualTo(Integer value) {
            addCriterion("attache_number <>", value, "attacheNumber");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberGreaterThan(Integer value) {
            addCriterion("attache_number >", value, "attacheNumber");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("attache_number >=", value, "attacheNumber");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberLessThan(Integer value) {
            addCriterion("attache_number <", value, "attacheNumber");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberLessThanOrEqualTo(Integer value) {
            addCriterion("attache_number <=", value, "attacheNumber");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberIn(List<Integer> values) {
            addCriterion("attache_number in", values, "attacheNumber");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberNotIn(List<Integer> values) {
            addCriterion("attache_number not in", values, "attacheNumber");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberBetween(Integer value1, Integer value2) {
            addCriterion("attache_number between", value1, value2, "attacheNumber");
            return (Criteria) this;
        }

        public Criteria andAttacheNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("attache_number not between", value1, value2, "attacheNumber");
            return (Criteria) this;
        }
        
        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }
        
        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(String value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(String value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(String value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(String value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(String value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLike(String value) {
            addCriterion("district like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotLike(String value) {
            addCriterion("district not like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<String> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<String> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(String value1, String value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(String value1, String value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andOtherIsNull() {
            addCriterion("other is null");
            return (Criteria) this;
        }

        public Criteria andOtherIsNotNull() {
            addCriterion("other is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEqualTo(String value) {
            addCriterion("other =", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotEqualTo(String value) {
            addCriterion("other <>", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThan(String value) {
            addCriterion("other >", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThanOrEqualTo(String value) {
            addCriterion("other >=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThan(String value) {
            addCriterion("other <", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThanOrEqualTo(String value) {
            addCriterion("other <=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLike(String value) {
            addCriterion("other like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotLike(String value) {
            addCriterion("other not like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherIn(List<String> values) {
            addCriterion("other in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotIn(List<String> values) {
            addCriterion("other not in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherBetween(String value1, String value2) {
            addCriterion("other between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotBetween(String value1, String value2) {
            addCriterion("other not between", value1, value2, "other");
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

//        public Criteria andReserveColumn01IsNull() {
//            addCriterion("reserve_column01 is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01IsNotNull() {
//            addCriterion("reserve_column01 is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01EqualTo(String value) {
//            addCriterion("reserve_column01 =", value, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01NotEqualTo(String value) {
//            addCriterion("reserve_column01 <>", value, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01GreaterThan(String value) {
//            addCriterion("reserve_column01 >", value, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01GreaterThanOrEqualTo(String value) {
//            addCriterion("reserve_column01 >=", value, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01LessThan(String value) {
//            addCriterion("reserve_column01 <", value, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01LessThanOrEqualTo(String value) {
//            addCriterion("reserve_column01 <=", value, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01Like(String value) {
//            addCriterion("reserve_column01 like", value, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01NotLike(String value) {
//            addCriterion("reserve_column01 not like", value, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01In(List<String> values) {
//            addCriterion("reserve_column01 in", values, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01NotIn(List<String> values) {
//            addCriterion("reserve_column01 not in", values, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01Between(String value1, String value2) {
//            addCriterion("reserve_column01 between", value1, value2, "reserveColumn01");
//            return (Criteria) this;
//        }
//
//        public Criteria andReserveColumn01NotBetween(String value1, String value2) {
//            addCriterion("reserve_column01 not between", value1, value2, "reserveColumn01");
//            return (Criteria) this;
//        }

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
        private final String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private final String typeHandler;

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