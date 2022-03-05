import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AddPostService} from "../add-post.service";
import {PostPayload} from "../add-post/post-payload";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  parmaLink!: Number
  post!: PostPayload

  constructor(private router:ActivatedRoute,
              private postSvc:AddPostService) { }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.parmaLink = params['id']
    })
    this.postSvc.getPost(this.parmaLink).subscribe((data:PostPayload)=> {
      this.post = data
    })
  }

}
