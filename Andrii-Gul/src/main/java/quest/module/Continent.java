package quest.module;

public enum Continent {
    Africa,
    Asia,
    Europe,
    NorthAmerica,
    SouthAmerica,
    Oceania,
    Antarctica;

    public static Continent fromString(String name) {
        if (name == null) throw new IllegalArgumentException("Континент не вказано");
        switch (name.trim().toLowerCase()) {
            case "африка": return Africa;
            case "європа": return Europe;
            case "азія": return Asia;
            case "північна америка": return NorthAmerica;
            case "південна америка": return SouthAmerica;
            case "океанія": return Oceania;
            case "антарктида": return Antarctica;
            default: throw new IllegalArgumentException("Невідомий континент: " + name);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case Africa: return "Африка";
            case Asia: return "Азія";
            case Europe: return "Європа";
            case NorthAmerica: return "Північна Америка";
            case SouthAmerica: return "Південна Америка";
            case Oceania: return "Океанія";
            case Antarctica: return "Антарктида";
            default: return super.toString();
        }
    }
}
