package Business;

import DAO.BrandDao;
import Entity.Brand;

import java.util.List;

public class BrandManager {
    private final BrandDao brandDao;

    public BrandManager() {
        this.brandDao = new BrandDao();
    }

    public List<Brand> findAllBrands() {
        return this.brandDao.findAllBrands();
    }

    public boolean saveBrand(Brand brand) {
        return this.brandDao.save(brand);
    }

    public boolean updateBrand(Brand brand) {
        return this.brandDao.update(brand);
    }

    public boolean deleteBrand(int id) {
        return this.brandDao.delete(id);
    }

    public Brand findBrandById(int id) {
        return this.brandDao.findById(id);
    }

    // Diğer BrandManager metotları
}
