import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {shareReplay, tap} from "rxjs/operators";
import {User} from "../users/user.service";

export interface Product {
  id: string,
  title: string,
  author: string,
  genre: string,
  publisher: string
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private BASE_URL = "http://localhost:8080/"

  private subject = new BehaviorSubject<Product[]>([])
  products$ = this.subject.asObservable()

  constructor(private http: HttpClient) {
    this.loadAllProducts()
  }

  loadAllProducts() {
    this.http.get<Product[]>(`${this.BASE_URL}/products`)
      .pipe(
        shareReplay(),
        tap(products => this.subject.next(products))
      ).subscribe()
  }

  deleteProductById(id: string) {
    const products = this.subject.value;

    this.subject.next(products.filter(product => product.id !== id))

    this.http.delete(`${this.BASE_URL}/products/${id}`).subscribe()

  }

  createProduct(product: Product) {
    const productList = this.subject.getValue();

    const newProducts: Product[] = productList.slice();
    newProducts.push(product);
    this.subject.next(newProducts);

    return this.http.post(`${this.BASE_URL}/products`, product)
      .pipe(
        shareReplay()
      )

  }
}
