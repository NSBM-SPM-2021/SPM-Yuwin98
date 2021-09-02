import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {v4} from "uuid";
import {User, UserService} from "../users/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-updateuser',
  templateUrl: './updateuser.component.html',
  styleUrls: ['./updateuser.component.css']
})
export class UpdateuserComponent implements OnInit {

  user: User;
  urlUserId: string = ""

  userForm: FormGroup = new FormGroup({
    userId: new FormControl('', [Validators.required]),
    userName: new FormControl( '', [Validators.required]),
    email: new FormControl('', [Validators.required])
  });

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) {
   this.urlUserId = this.route.snapshot.params.id;
  }

  get userId() {
    return this.userForm.get('userId') as FormControl;
  }

  get userName() {
    return this.userForm.get('userName') as FormControl;
  }


  get email() {
    return this.userForm.get('email') as FormControl;
  }

  ngOnInit(): void {
    this.user = this.userService.findUser(this.urlUserId);
    this.userForm.get("userId")?.setValue(this.user.userId);
    this.userForm.get("email")?.setValue(this.user.email);
    this.userForm.get("userName")?.setValue(this.user.userName);
  }

  updateUser() {
    if(!this.userForm.valid) {
      console.log(this.userForm.value);
    }

    console.log(this.userForm.value);
    this.userService.updateUser(this.userForm.value).subscribe({
      next: () => {
        this.router.navigateByUrl("/users")
      }
    });
  }

  cancelUpdate() {
    this.router.navigateByUrl("/users")
  }

}
