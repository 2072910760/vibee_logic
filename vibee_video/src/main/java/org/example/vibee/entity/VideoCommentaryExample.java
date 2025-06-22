package org.example.vibee.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoCommentaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VideoCommentaryExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andInteractionIdIsNull() {
            addCriterion("interaction_id is null");
            return (Criteria) this;
        }

        public Criteria andInteractionIdIsNotNull() {
            addCriterion("interaction_id is not null");
            return (Criteria) this;
        }

        public Criteria andInteractionIdEqualTo(Integer value) {
            addCriterion("interaction_id =", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdNotEqualTo(Integer value) {
            addCriterion("interaction_id <>", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdGreaterThan(Integer value) {
            addCriterion("interaction_id >", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("interaction_id >=", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdLessThan(Integer value) {
            addCriterion("interaction_id <", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdLessThanOrEqualTo(Integer value) {
            addCriterion("interaction_id <=", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdIn(List<Integer> values) {
            addCriterion("interaction_id in", values, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdNotIn(List<Integer> values) {
            addCriterion("interaction_id not in", values, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdBetween(Integer value1, Integer value2) {
            addCriterion("interaction_id between", value1, value2, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("interaction_id not between", value1, value2, "interactionId");
            return (Criteria) this;
        }

        public Criteria andVideoIdIsNull() {
            addCriterion("video_id is null");
            return (Criteria) this;
        }

        public Criteria andVideoIdIsNotNull() {
            addCriterion("video_id is not null");
            return (Criteria) this;
        }

        public Criteria andVideoIdEqualTo(Integer value) {
            addCriterion("video_id =", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotEqualTo(Integer value) {
            addCriterion("video_id <>", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdGreaterThan(Integer value) {
            addCriterion("video_id >", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("video_id >=", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLessThan(Integer value) {
            addCriterion("video_id <", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLessThanOrEqualTo(Integer value) {
            addCriterion("video_id <=", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdIn(List<Integer> values) {
            addCriterion("video_id in", values, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotIn(List<Integer> values) {
            addCriterion("video_id not in", values, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdBetween(Integer value1, Integer value2) {
            addCriterion("video_id between", value1, value2, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("video_id not between", value1, value2, "videoId");
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

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andInteractionContentIsNull() {
            addCriterion("interaction_content is null");
            return (Criteria) this;
        }

        public Criteria andInteractionContentIsNotNull() {
            addCriterion("interaction_content is not null");
            return (Criteria) this;
        }

        public Criteria andInteractionContentEqualTo(String value) {
            addCriterion("interaction_content =", value, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentNotEqualTo(String value) {
            addCriterion("interaction_content <>", value, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentGreaterThan(String value) {
            addCriterion("interaction_content >", value, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentGreaterThanOrEqualTo(String value) {
            addCriterion("interaction_content >=", value, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentLessThan(String value) {
            addCriterion("interaction_content <", value, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentLessThanOrEqualTo(String value) {
            addCriterion("interaction_content <=", value, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentLike(String value) {
            addCriterion("interaction_content like", value, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentNotLike(String value) {
            addCriterion("interaction_content not like", value, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentIn(List<String> values) {
            addCriterion("interaction_content in", values, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentNotIn(List<String> values) {
            addCriterion("interaction_content not in", values, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentBetween(String value1, String value2) {
            addCriterion("interaction_content between", value1, value2, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionContentNotBetween(String value1, String value2) {
            addCriterion("interaction_content not between", value1, value2, "interactionContent");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeIsNull() {
            addCriterion("interaction_time is null");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeIsNotNull() {
            addCriterion("interaction_time is not null");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeEqualTo(Date value) {
            addCriterion("interaction_time =", value, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeNotEqualTo(Date value) {
            addCriterion("interaction_time <>", value, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeGreaterThan(Date value) {
            addCriterion("interaction_time >", value, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("interaction_time >=", value, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeLessThan(Date value) {
            addCriterion("interaction_time <", value, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeLessThanOrEqualTo(Date value) {
            addCriterion("interaction_time <=", value, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeIn(List<Date> values) {
            addCriterion("interaction_time in", values, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeNotIn(List<Date> values) {
            addCriterion("interaction_time not in", values, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeBetween(Date value1, Date value2) {
            addCriterion("interaction_time between", value1, value2, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andInteractionTimeNotBetween(Date value1, Date value2) {
            addCriterion("interaction_time not between", value1, value2, "interactionTime");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdIsNull() {
            addCriterion("reply_to_comment_id is null");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdIsNotNull() {
            addCriterion("reply_to_comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdEqualTo(Integer value) {
            addCriterion("reply_to_comment_id =", value, "replyToCommentId");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdNotEqualTo(Integer value) {
            addCriterion("reply_to_comment_id <>", value, "replyToCommentId");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdGreaterThan(Integer value) {
            addCriterion("reply_to_comment_id >", value, "replyToCommentId");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_to_comment_id >=", value, "replyToCommentId");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdLessThan(Integer value) {
            addCriterion("reply_to_comment_id <", value, "replyToCommentId");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("reply_to_comment_id <=", value, "replyToCommentId");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdIn(List<Integer> values) {
            addCriterion("reply_to_comment_id in", values, "replyToCommentId");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdNotIn(List<Integer> values) {
            addCriterion("reply_to_comment_id not in", values, "replyToCommentId");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("reply_to_comment_id between", value1, value2, "replyToCommentId");
            return (Criteria) this;
        }

        public Criteria andReplyToCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_to_comment_id not between", value1, value2, "replyToCommentId");
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