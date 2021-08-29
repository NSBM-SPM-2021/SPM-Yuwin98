import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {UsersComponent} from "./users/users.component";
import {ProductComponent} from "./product/product.component";
import {AboutComponent} from "./about/about.component";
import {AdduserComponent} from "./adduser/adduser.component";
import {AddproductComponent} from "./addproduct/addproduct.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: 'products',
    component: ProductComponent
  },
  {
    path: 'about',
    component: AboutComponent
  },
  {
    path: 'users/new',
    component: AdduserComponent
  },
  {
    path: 'products/new',
    component: AddproductComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
