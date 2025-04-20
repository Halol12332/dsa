package dao;

import adt.*;
import entities.Company;

public class CompanyInitializer {
    private final CompanyDAO companyDAO;

    public CompanyInitializer() {
        this.companyDAO = new CompanyDAO();
    }

    public void initializeCompanies() {
        ListInterface<Company> cList = new LinkedList<>();
        cList.add(new Company("C001", "Acclime", "Finance", "Kuala Lumpur"));
        cList.add(new Company("C002", "IBM", "Technology", "Kuala Lumpur"));
        cList.add(new Company("C003", "WD", "Web Design", "Selangor"));
        cList.add(new Company("C004", "SE", "Software Engineering", "Johor Bahru"));
        cList.add(new Company("C005", "Expedio", "Product Design", "Melaka"));
        cList.add(new Company("C006", "UCCI University", "Education", "Negeri Sembilan"));
        cList.add(new Company("C007", "Nexx Studio", "Game Development", "Selangor"));

        companyDAO.saveToFile(cList);
    }

    public static void main(String[] args) {
        CompanyInitializer ci = new CompanyInitializer();
        ci.initializeCompanies();
        ListInterface<Company> loadedCompanies = ci.companyDAO.retrieveFromFile();
        System.out.println("Companies:");
        for (int i = 1; i <= loadedCompanies.getNumberOfEntries(); i++) {
            System.out.println(loadedCompanies.getEntry(i));
        }
    }
}