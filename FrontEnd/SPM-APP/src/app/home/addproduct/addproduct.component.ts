import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {v4} from "uuid";
import {UserService} from "../users/user.service";
import {ProductService} from "../product/product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})
export class AddproductComponent implements OnInit {

  productForm = new FormGroup({
    id: new FormControl(`${v4().toString().split('-').join('')}`, [Validators.required]),
    title: new FormControl('', [Validators.required]),
    author: new FormControl('', [Validators.required]),
    genre: new FormControl('', [Validators.required]),
    publisher: new FormControl('', [Validators.required])
  });

  constructor(private productService: ProductService, private router: Router) {
  }

  get productId() {
    return this.productForm.get('id') as FormControl;
  }

  get title() {
    return this.productForm.get('title') as FormControl;
  }

  get author() {
    return this.productForm.get('author') as FormControl;
  }

  get genre() {
    return this.productForm.get('genre') as FormControl;
  }

  get publisher() {
    return this.productForm.get('publisher') as FormControl;
  }

  ngOnInit(): void {
  }

  addProduct() {
    if(!this.productForm.valid) {
      console.log("Form invalid");
      console.log(this.productForm.value);
    }

    console.log(this.productForm.value);
    this.productService.createProduct(this.productForm.value).subscribe({
      next: () => {
        this.router.navigateByUrl("/products")
        window.location.reload();
      }
    });
  }
}
