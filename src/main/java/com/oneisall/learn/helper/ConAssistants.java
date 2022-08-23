package com.oneisall.learn.helper;

import com.oneisall.learn.java.utils.CollectionUtil;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author liuzhicong
 **/
@Slf4j
public class ConAssistants<V> {

    private static final Executor DEFAULT_EXECUTOR;

    static {
        DEFAULT_EXECUTOR = Executors.newCachedThreadPool();
    }

    private final List<? extends ConQuestion> questions;

    private final Function<ConQuestion, V> answerWay;

    private final Map<String, V> answers;

    private final Executor assistantExecutor;

    // inner class

    enum FailReason {
        EXCEPTION, NULL
    }

    @ToString
    static class DefaultConQuestion implements ConQuestion {

        private final String key;

        public DefaultConQuestion(Object key) {
            this.key = Objects.isNull(key) ? "" : String.valueOf(key);
        }

        @Override
        public String questionKey() {
            return key;
        }
    }

    // 预留排查

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
    private final Map<String, FailReason> failRecords = new HashMap<>();

    @SuppressWarnings("unchecked")
    public ConAssistants(List<? extends ConQuestion> questions, Function<? extends ConQuestion, V> answerWay, Executor assistantExecutor) {
        this.questions = CollectionUtil.null2empty(questions);
        this.answers = new ConcurrentHashMap<>();
        this.assistantExecutor = assistantExecutor == null ? DEFAULT_EXECUTOR : assistantExecutor;
        this.answerWay = (Function<ConQuestion, V>) answerWay;
    }

    public ConAssistants(List<? extends ConQuestion> questions, Function<? extends ConQuestion, V> answerWay) {
        this(questions, answerWay, null);
    }

    public static <V> ConAssistants<V> build(List<?> commonQuestions, Function<? extends ConQuestion, V> commonAnswerWay) {
        List<DefaultConQuestion> questionList = CollectionUtil.null2empty(commonQuestions).stream()
                .map(DefaultConQuestion::new)
                .collect(Collectors.toList());

        return new ConAssistants<>(questionList, commonAnswerWay);
    }

    public void work() {

        if (CollectionUtil.isEmpty(questions)) {
            return;
        }

        List<CompletableFuture<Void>> futuresList = new ArrayList<>(questions.size());
        for (ConQuestion question : questions) {
            String questionKey = question.questionKey();
            CompletableFuture<Void> cf = CompletableFuture
                    .runAsync(() -> {
                                V answer = answerWay.apply(question);
                                if (answer != null) {
//                                    System.out.println("获取到结果" + question);
                                    answers.put(questionKey, answer);
                                } else {
                                    failRecords.put(questionKey, FailReason.NULL);
                                    log.error("The assistant get the null value , questionKey={}", questionKey);
                                }
                            },
                            assistantExecutor)
                    .exceptionally(e -> {
                        Throwable cause = e.getCause() == null ? e : e.getCause();
                        log.error("An cause occurred while the assistant was working , questionKey={}", questionKey, cause);
                        failRecords.put(questionKey, FailReason.EXCEPTION);
                        return null;
                    });
            futuresList.add(cf);
        }
        CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[0])).join();
//        System.out.println("执行完毕" + questions);
    }

    public V getAnswer(String key) {
        return answers.get(key);
    }

    public boolean checkFail(String key) {
        return failRecords.containsKey(key) || getAnswer(key) == null;
    }
}
