import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UsersComponent} from "./users/users.component";
import {ProductComponent} from "./product/product.component";
import {AboutComponent} from "./about/about.component";
import {AdduserComponent} from "./adduser/adduser.component";
import {AddproductComponent} from "./addproduct/addproduct.component";
import {UpdateuserComponent} from "./updateuser/updateuser.component";
import {UpdateproductComponent} from "./updateproduct/updateproduct.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: "users",
    pathMatch: 'full'
  },
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: 'users/update/:id',
    component: UpdateuserComponent
  },
  {
    path: 'products',
    component: ProductComponent
  },
  {
    path: 'products/update/:id',
    component: UpdateproductComponent
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
  },
  {path: '**', redirectTo: 'users'}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {
}
