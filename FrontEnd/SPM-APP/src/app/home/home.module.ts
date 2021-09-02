import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { UsersComponent } from './users/users.component';
import { ProductComponent } from './product/product.component';
import { AboutComponent } from './about/about.component';
import { AdduserComponent } from './adduser/adduser.component';
import { AddproductComponent } from './addproduct/addproduct.component';
import {ReactiveFormsModule} from "@angular/forms";
import { UpdateuserComponent } from './updateuser/updateuser.component';
import { UpdateproductComponent } from './updateproduct/updateproduct.component';


@NgModule({
  declarations: [
    UsersComponent,
    ProductComponent,
    AboutComponent,
    AdduserComponent,
    AddproductComponent,
    UpdateuserComponent,
    UpdateproductComponent
  ],
    imports: [
        CommonModule,
        HomeRoutingModule,
        ReactiveFormsModule
    ]
})
export class HomeModule { }
