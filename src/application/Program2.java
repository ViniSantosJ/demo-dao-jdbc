package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department department = null;

        System.out.print("Digite o nome de um novo departamento: ");
        String n = sc.nextLine();
        System.out.println();
        System.out.println("=== TESTE 1: department insert ===");
        Department newDepartment = new Department(null, n);
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());

        System.out.print("\nDigite um id para deletar: ");
        int id = sc.nextInt();
        System.out.println();

        System.out.println("=== TESTE 2: department delete ===");
        departmentDao.deleteById(id);

        System.out.print("\nDigite um id para localizar: ");
        id = sc.nextInt();
        System.out.println();
        System.out.println("=== TESTE 3: department findById ===");
        department = departmentDao.findById(id);
        System.out.println(department);

        System.out.println();
        System.out.print("Digite um id para atualizar: ");
        id = sc.nextInt();
        System.out.print("Digite o novo nome: ");
        n = sc.next();
        System.out.println("=== TESTE 4: department updateById");
        department = departmentDao.findById(id);
        department.setName(n);
        departmentDao.update(department);

        System.out.println();
        System.out.println("=== TESTE 5: department findAll ===");
        List<Department> list = departmentDao.findAll();
        for (Department obj : list) {
            System.out.println(obj);
        }




        sc.close();
    }
}
