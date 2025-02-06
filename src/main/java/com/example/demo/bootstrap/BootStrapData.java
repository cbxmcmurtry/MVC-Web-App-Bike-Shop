package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final InhousePartRepository inhousePartRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository,
                         InhousePartRepository inhousePartRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.inhousePartRepository = inhousePartRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        long partCount = partRepository.count();
        long productCount = productRepository.count();

        if (partCount == 0 && productCount == 0) {
            addSampleInventory();
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts: " + partRepository.count());
        System.out.println(partRepository.findAll());
    }

    private void addSampleInventory() {
        InhousePart wheels = new InhousePart("Wheels", 25.0, 100, 10, 200);
        inhousePartRepository.save(wheels);

        InhousePart trucks = new InhousePart("Trucks", 35.0, 100, 5, 150);
        inhousePartRepository.save(trucks);

        InhousePart gripTape = new InhousePart("Grip Tape", 10.0, 200, 50, 500);
        inhousePartRepository.save(gripTape);

        InhousePart board = new InhousePart("Board", 50.0, 150, 20, 300);
        inhousePartRepository.save(board);

        InhousePart bearings = new InhousePart("Bearings", 20.0, 300, 30, 600);
        inhousePartRepository.save(bearings);

        OutsourcedPart outsourcedWheels = new OutsourcedPart("Outsourced Wheels", 30.0, 100, 10, 200, "Company A");
        outsourcedPartRepository.save(outsourcedWheels);

        OutsourcedPart outsourcedTrucks = new OutsourcedPart("Outsourced Trucks", 40.0, 100, 5, 150, "Company B");
        outsourcedPartRepository.save(outsourcedTrucks);

        OutsourcedPart outsourcedGripTape = new OutsourcedPart("Outsourced Grip Tape", 15.0, 200, 50, 500, "Company C");
        outsourcedPartRepository.save(outsourcedGripTape);

        OutsourcedPart outsourcedBoard = new OutsourcedPart("Outsourced Board", 55.0, 150, 20, 300, "Company D");
        outsourcedPartRepository.save(outsourcedBoard);

        OutsourcedPart outsourcedBearings = new OutsourcedPart("Outsourced Bearings", 25.0, 300, 30, 600, "Company E");
        outsourcedPartRepository.save(outsourcedBearings);

        // Adding sample products
        Product skateboard = new Product("Skateboard", 100.0, 10);
        productRepository.save(skateboard);

        Product pintail = new Product("Pintail", 120.0, 15);
        productRepository.save(pintail);

        Product longboard = new Product("Longboard", 150.0, 5);
        productRepository.save(longboard);

        Product pennyboard = new Product("Pennyboard", 75.0, 25);
        productRepository.save(pennyboard);

        Product cruiser = new Product("Cruiser", 130.0, 8);
        productRepository.save(cruiser);
    }
}
