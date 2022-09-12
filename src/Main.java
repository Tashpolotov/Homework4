import java.util.Random;

public class Main {
    public static int bossHealth = 950;
    public static int bossDamage = 45;
    public static String bossDefenceType;
    public static int[] heroesHealth = {250, 260, 270, 180, 300, 250, 150, 500};
    public static int[] heroesDamage = {25, 20, 15, 0, 35, 20, 10, 2};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "medic", "lucky", "berserk",
            "Thor", "Golem"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        playRound();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        medicHill();
        lucky();
        berserk();
        Thor();
        Golem();
        printStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " -------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: " + bossDamage + "; defence: "
                + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + "; damage: " + heroesDamage[i]);
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(8) + 2; // 2,3,4,5,6,7,8,9
                    hit = coeff * heroesDamage[i];
                    System.out.println("Critical damage of ROUND " + roundNumber + ": " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }
            }
        }
    }

    public static void medicHill() {
        Random random = new Random();
        int Hill = random.nextInt(15);
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[1] > 0 && heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                heroesHealth[i] = heroesHealth[i] + Hill;
                System.out.println("Hill: " + heroesAttackType[i] + "  " + Hill + "HP");
                break;
            } else {
                System.out.println("Medic not Hill");
                break;
            }
        }
    }

    public static void lucky() {
        Random random = new Random();
        boolean lucky = random.nextBoolean();
        if (heroesHealth[4] > 0) {
            if (lucky) heroesHealth[4] += bossDamage - 10;

            System.out.println("lucky Увернулся от удара: " + lucky);
        }
    }

    public static void berserk() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[5] > 0) ;
            heroesHealth[5] -= bossDamage * 1 / 5;
            heroesHealth[5] = heroesHealth[6] + bossDamage * 1 / 5;
            System.out.println("Berserk blok bossdamage");
            break;
        }


    }

    public static void Thor() {
        Random random = new Random();
        boolean thorHill = random.nextBoolean();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[6] > 0) ;
            {
                if (thorHill) {
                    bossDamage = 0;

                    System.out.println("Thor oglushil: " + thorHill);
                    break;
                } else {
                    bossDamage = 50;
                    break;
                }
            }
        }
    }   public static void Golem() {
        int UronBoss = bossDamage / 5;
        int AliveHeroes = 0;
        if (heroesHealth[7] > 0) {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (i == 4) {
                    continue;
                } else if (heroesHealth[i] > 0)
                    AliveHeroes++;
                heroesHealth[i] += UronBoss;
            }
        }
        heroesHealth[7] -= UronBoss * AliveHeroes;
        System.out.println("Golem получает урон = " + UronBoss * AliveHeroes);
    }
}

















