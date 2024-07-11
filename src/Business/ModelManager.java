package Business;

import DAO.ModelDao;
import Entity.Model;

import java.util.List;

public class ModelManager {
    private final ModelDao modelDao;

    public ModelManager() {
        this.modelDao = new ModelDao();
    }

    public List<Model> findAllModels() {
        return this.modelDao.findAllModels();
    }

    public boolean saveModel(Model model) {
        return this.modelDao.save(model);
    }

    public boolean updateModel(Model model) {
        return this.modelDao.update(model);
    }

    public boolean deleteModel(int id) {
        return this.modelDao.delete(id);
    }

    public Model findModelById(int id) {
        return this.modelDao.findById(id);
    }
}
