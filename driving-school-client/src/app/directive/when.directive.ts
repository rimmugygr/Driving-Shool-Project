import {Directive, OnInit, TemplateRef, ViewContainerRef} from '@angular/core';

@Directive({
  selector: '[appWhen]'
})
export class WhenDirective implements OnInit{

  constructor(
    private template: TemplateRef<any>,
    private container: ViewContainerRef
  ) { }

  ngOnInit(): void {
    this.container.createEmbeddedView(this.template);
  }

}
