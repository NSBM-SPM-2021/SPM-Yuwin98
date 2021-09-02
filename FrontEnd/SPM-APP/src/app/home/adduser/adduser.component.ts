import {Component, OnInit} from '@angular/core';
import {User, UserService} from "../users/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

import {v4} from 'uuid';
import {Router} from "@angular/router";


@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.component.html',
  styleUrls: ['./adduser.component.css']
})
export class AdduserComponent implements OnInit {

  userForm = new FormGroup({
    userId: new FormControl(`${v4().toString().split('-').join('')}`, [Validators.required]),
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required])
  });

  constructor(private userService: UserService, private router: Router) {
  }

  get userId() {
    return this.userForm.get('userId') as FormControl;
  }

  get userName() {
    return this.userForm.get('userName') as FormControl;
  }

  get password() {
    return this.userForm.get('password') as FormControl;
  }

  get email() {
    return this.userForm.get('email') as FormControl;
  }

  ngOnInit(): void {
  }

  addUser() {
    if(!this.userForm.valid) {
      console.log(this.userForm.value);
    }

    console.log(this.userForm.value);
    this.userService.createUser(this.userForm.value).subscribe({
      next: () => {
        this.router.navigateByUrl("/users")
        window.location.reload();
      }
    });
  }

}
