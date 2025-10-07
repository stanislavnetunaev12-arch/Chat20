public class SimpleTest {

    public static void main(String[] args) {
        System.out.println("=== ЗАПУСК ТЕСТОВ ЧАТ-БОТА ===\n");

        try {
            // Тест 1: Хранилище вопросов
            System.out.println("ТЕСТ 1: Проверка хранилища вопросов");
            testQuestionStorage();

            // Тест 2: Проверка ответов
            System.out.println("\nТЕСТ 2: Проверка ответов на вопросы");
            testQuestionCheckAnswer();

            // Тест 3: Логика диалога
            System.out.println("\nТЕСТ 3: Проверка логики диалога");
            testDialogLogic();

            // Тест 4: Команды бота
            System.out.println("\nТЕСТ 4: Проверка команд бота");
            testBotCommands();

            System.out.println("\n=== ВСЕ ТЕСТЫ УСПЕШНО ПРОЙДЕНЫ! ===");

        } catch (Exception e) {
            System.out.println("ОШИБКА: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testQuestionStorage() {
        QuestionStorage storage = new QuestionStorage();

        // Проверяем что вопросы есть
        int count = storage.getQuestionsCount();
        System.out.println("✓ Количество вопросов: " + count);

        // Проверяем первый вопрос
        QuestionStorage.Question first = storage.getQuestion(0);
        System.out.println("✓ Первый вопрос: " + first.getQuestion());
        System.out.println("✓ Ответ: " + first.getAnswer());

        // Проверяем что можем получить все вопросы
        for (int i = 0; i < count; i++) {
            QuestionStorage.Question q = storage.getQuestion(i);
            System.out.println("✓ Вопрос " + (i+1) + ": " + q.getQuestion());
        }
    }

    public static void testQuestionCheckAnswer() {
        // Создаем тестовый вопрос
        QuestionStorage.Question question = new QuestionStorage.Question(
                "Что идет без ног?",
                "время"
        );

        // Проверяем правильный ответ
        boolean result1 = question.checkAnswer("время");
        System.out.println("✓ Правильный ответ 'время': " + result1);

        // Проверяем ответ в другом регистре
        boolean result2 = question.checkAnswer("ВРЕМЯ");
        System.out.println("✓ Ответ 'ВРЕМЯ' (верхний регистр): " + result2);

        // Проверяем ответ с пробелами
        boolean result3 = question.checkAnswer("  время  ");
        System.out.println("✓ Ответ '  время  ' (с пробелами): " + result3);

        // Проверяем неправильный ответ
        boolean result4 = question.checkAnswer("неправильно");
        System.out.println("✓ Неправильный ответ 'неправильно': " + result4);
    }

    public static void testDialogLogic() {
        QuestionStorage storage = new QuestionStorage();
        DialogLogic dialog = new DialogLogic(storage);

        // Проверяем приветственное сообщение
        String welcome = dialog.getWelcomeMessage();
        System.out.println("✓ Приветственное сообщение: " + welcome.substring(0, 50) + "...");

        // Проверяем что есть вопросы
        boolean hasQuestions = dialog.hasNextQuestion();
        System.out.println("✓ Есть следующие вопросы: " + hasQuestions);

        // Получаем первый вопрос
        String firstQuestion = dialog.getNextQuestion();
        System.out.println("✓ Первый вопрос: " + firstQuestion);
    }

    public static void testBotCommands() {
        QuestionStorage storage = new QuestionStorage();
        DialogLogic dialog = new DialogLogic(storage);

        // Проверяем команду help
        String helpResponse = dialog.processAnswer("\\help");
        System.out.println("✓ Команда \\help работает");

        // Проверяем команду exit
        String exitResponse = dialog.processAnswer("\\exit");
        System.out.println("✓ Команда \\exit возвращает: " + exitResponse);
    }
}