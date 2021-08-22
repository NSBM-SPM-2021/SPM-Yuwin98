import { Component, OnInit } from '@angular/core';
import {User, UserService} from "./user.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users$: Observable<User[]> = new Observable()

  constructor(private userServices: UserService) {

  }

  ngOnInit(): void {
    this.users$ = this.userServices.users$;
  }

  deleteUser(id: string) {
    this.userServices.deleteUserById(id);
  }

}
