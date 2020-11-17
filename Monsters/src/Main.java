public class Main {
    public static void main(final String[] args) {
        final Army army = new Army();

        for (int i = 0; i < 8; i++) {
            army.addMonster(new Monster("Pikachu" + i, 100));
        }

        army.debugDisplay();
        System.out.println();
        army.delMonster("Pikachu0");
        army.delMonster("Pikachu3");
        army.delMonster("Pikachu6");
        army.debugDisplay();
    }
}
