package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department department = null;

        System.out.println("Digite o nome de um novo departamento: ");
        String n = sc.nextLine();

        System.out.println("=== TESTE 1: department insert ===");
        Department newDepartment = new Department(null, n);
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());

        System.out.println("\nDigite um id para deletar: ");
        int id = sc.nextInt();
        System.out.println();

        System.out.println("=== TESTE 2: department delete ===");
        departmentDao.deleteById(id);

        System.out.println("\nDigite um id para localizar: ");
        id = sc.nextInt();
        System.out.println();
        System.out.println("=== TESTE 3: department findById ===");
        department = departmentDao.findById(id);
        System.out.println(department);

        sc.close();
    }
}
