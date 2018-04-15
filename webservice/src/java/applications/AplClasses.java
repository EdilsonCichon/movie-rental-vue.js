package applications;

import dao.DaoClasses;
import domains.Classe;
import javax.json.JsonObject;

public class AplClasses extends AplBase {

    public AplClasses() {
        this.dao = new DaoClasses();
    }

    public void save(JsonObject data) throws Exception {
        String name = data.getString("name");
        double value = Double.valueOf(data.getString("value"));
        double maximumRentalTime = Double.valueOf(data.getString("maximumRentalTime"));
        if (name.equals(""))
            throw new Exception("[name] not filled.");
        Classe classe = new Classe(name, value, maximumRentalTime);
        super.save(classe);
    }
    
    public void delete(int id) throws Exception {
        Classe classe = (Classe) this.get(id);
        this.delete(classe);
    }
    
    public void update(int id, JsonObject data) throws Exception {
        String name = data.getString("name");
        double value = Double.valueOf(data.getString("value"));
        double maximumRentalTime = Double.valueOf(data.getString("maximumRentalTime"));
        if (name.equals(""))
            throw new Exception("[name] not filled.");
        Classe classe = (Classe) this.get(id);
        classe.setName(name);
        classe.setValue(value);
        classe.setMaximumRentalTime(maximumRentalTime);
        this.update(classe);
    }
}
