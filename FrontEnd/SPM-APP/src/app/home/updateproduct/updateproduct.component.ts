import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Product, ProductService} from "../product/product.service";

@Component({
  selector: 'app-updateproduct',
  templateUrl: './updateproduct.component.html',
  styleUrls: ['./updateproduct.component.css']
})
export class UpdateproductComponent implements OnInit {

  product: Product;
  urlUserId: string = ""

  productForm: FormGroup = new FormGroup({
    id: new FormControl('', [Validators.required]),
    title: new FormControl('', [Validators.required]),
    author: new FormControl('', [Validators.required]),
    genre: new FormControl('', [Validators.required]),
    publisher: new FormControl('', [Validators.required])
  });

  constructor(private productService: ProductService, private router: Router,
              private route: ActivatedRoute) {
    this.urlUserId = this.route.snapshot.params.id;
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
    this.product = this.productService.findProduct(this.urlUserId);
    this.productForm.get("id")?.setValue(this.product.id);
    this.productForm.get("title")?.setValue(this.product.title);
    this.productForm.get("author")?.setValue(this.product.author);
    this.productForm.get("genre")?.setValue(this.product.genre);
    this.productForm.get("publisher")?.setValue(this.product.publisher);
  }

  updateProduct() {
    if (!this.productForm.valid) {
      console.log(this.productForm.value);
    }

    console.log(this.productForm.value);
    this.productService.updateProduct(this.productForm.value).subscribe({
      next: () => {
        this.router.navigateByUrl("/products");
      }
    });
  }

  cancelUpdate() {
    this.router.navigateByUrl("/products")
  }

}
