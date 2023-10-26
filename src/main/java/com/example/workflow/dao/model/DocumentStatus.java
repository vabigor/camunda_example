package com.example.workflow.dao.model;

import java.util.*;

public enum DocumentStatus {

    /**
     * Черновик
     */
    DRAFT("Черновик"),

    /**
     * Новый
     */
    NEW("Новый"),

    /**
     * Утверждение
     */
    STATEMENT("На утверждении"),

    /**
     * Утвержден
     */
    APPROVED("Утвержден"),

    /**
     * Подписание
     */
    SIGNING("На подписании"),

    /**
     * Подписан
     */
    SIGNED("Подписан"),

    /**
     * Согласование
     */
    AGREEMENT("На согласовании"),

    /**
     * Согласован
     */
    AGREED("Согласован"),

    /**
     * Рассмотрение
     */
    REVIEW("На рассмотрении"),

    /**
     * Расммотрен
     */
    REVIEWED("Рассмотрен"),

    /**
     * Регистрация
     */
    REGISTRATION("На регистрации"),

    /**
     * Зарегистрирован
     */
    REGISTERED("Зарегистрирован"),

    /**
     * Исполнение
     */
    EXECUTION("На исполнении"),

    /**
     * Исполнен
     */
    EXECUTED("Исполнен"),

    /**
     * Отправка
     */
    DISPATCH("На отправке"),

    /**
     * Отправлен
     */
    SENT("Отправлен"),

    /**
     * Отклонен
     */
    REJECT("Отклонен"),

    /**
     * Ануллирован
     */
    CANCEL("Аннулирован"),

    /**
     * Подготовка
     */
    PREPARATION("Подготовка"),

    /**
     * Оформление
     */
    PAPERWORK("На оформлении"),

    /**
     * Оформлен
     */
    FORMALIZED("Оформлен"),

    /**
     * Одобрение
     */
    ACCEPTANCE("На одобрении"),

    /**
     * Одобрен
     */
    ACCEPTED("Одобрен"),

    /**
     * Архив
     */
    ARCHIVE("ARCHIVE"),

    /**
     * Удален
     */
    DELETED("DELETED"),

    /**
     * Все
     */
    ALL("Все");

    private String status;

    DocumentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static DocumentStatus[] valuesExcept(DocumentStatus...statuses){
        Set<DocumentStatus> s = new HashSet<>(statuses.length+4);
        for (DocumentStatus status : statuses) {
            s.add(status);
        }
        s.add(DocumentStatus.ALL);
        s.add(DocumentStatus.DRAFT);
        s.add(DocumentStatus.DELETED);
        s.add(DocumentStatus.ARCHIVE);
        List<DocumentStatus> result = new ArrayList<>(DocumentStatus.values().length-statuses.length);
        for (DocumentStatus value : DocumentStatus.values()) {
            if (!s.contains(value)){
                result.add(value);
            }
        }
        return result.toArray(result.toArray(new DocumentStatus[0]));
    }

    public static DocumentStatus findByValue(final String name) {
        return Arrays.stream(values()).filter(value -> value.getStatus().equals(name)).findFirst().orElse(null);
    }
}
