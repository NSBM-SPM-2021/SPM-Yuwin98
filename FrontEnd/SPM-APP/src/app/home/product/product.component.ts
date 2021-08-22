import { Component, OnInit } from '@angular/core';
import {Product, ProductService} from "./product.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products$ = new Observable<Product[]>()

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.products$ = this.productService.products$;
  }

  deleteProduct(id: string) {
    this.productService.deleteProductById(id);
  }

}
